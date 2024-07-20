package com.Lubee.Lubee.couple.service;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.ErrorResponse;
import com.Lubee.Lubee.common.api.ResponseUtils;
import com.Lubee.Lubee.common.enumSet.ErrorType;
import com.Lubee.Lubee.common.exception.RestApiException;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.couple.dto.LubeeCodeResponse;
import com.Lubee.Lubee.couple.repository.CoupleRepository;
import com.Lubee.Lubee.user.domain.User;
import com.Lubee.Lubee.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CoupleService {

    private static final long LUBEE_CODE_EXPIRATION_MINUTES = 1440; // 24시간(분 단위)

    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;

    @Autowired
    private RedisTemplate<Long, String> redisTemplate;      // key-userid, value-lubeecode

    @Autowired
    private RedisTemplate<String, Long> reverseRedisTemplate;      // key-lubeecode, value-userid

    /**
     * <러비코드 생성/조회>
     *     - 10자리의 랜덤한 코드 생성
     *     - Userid가 key인 redisTemplate 생성
     *     - lubeecode가 key인 reverseRedisTemplate 생성
     */
    @Transactional
    public ApiResponseDto<LubeeCodeResponse> getLubeeCode(UserDetails userDetails) {

        final User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));

        String lubeeCode = redisTemplate.opsForValue().get(user.getId());
        if (lubeeCode == null) {        // 기존에 lubeecode 없으면 새로 생성
            lubeeCode = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            //throw new RestApiException(ErrorType.LUBEE_CODE_NOT_FOUND);
        }

        // 저장
        redisTemplate.opsForValue().set(user.getId(), lubeeCode, Duration.ofMinutes(LUBEE_CODE_EXPIRATION_MINUTES));
        reverseRedisTemplate.opsForValue().set(lubeeCode, user.getId(),Duration.ofMinutes(LUBEE_CODE_EXPIRATION_MINUTES));

        return ResponseUtils.ok(LubeeCodeResponse.of(lubeeCode), ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    /**
     * <러비코드 조회>
     *     - userId가 가지고 있는 러비코드 조회
     *     - 생성된 러비코드가 없을시, 에러 반환
     */
    /*@Transactional(readOnly = true)
    public ApiResponseDto<LubeeCodeResponse> findLubeeCode(Long userid) {

        final User user = userRepository.findById(userid)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));

        String lubeeCode = redisTemplate.opsForValue().get(user.getId());
        if (lubeeCode == null) {
            throw new RestApiException(ErrorType.LUBEE_CODE_NOT_FOUND);
        }

        return ResponseUtils.ok(LubeeCodeResponse.of(lubeeCode), ErrorResponse.builder().status(200).message("러비코드 조회 성공").build());
    }*/

    /**
     * <커플 연동>
     *     - requester, receiver가 이미 커플인지 확인한 후 커플 연동
     *     - 두 user가 커플 연결됐을 경우, DB에서 두 러비코드 삭제
     */
    @Transactional
    public ApiResponseDto<Long> linkCouple(UserDetails userDetails, String inputCode) {

        User requester = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));
        if (requester.isAlreadyCouple()) {
            throw new RestApiException(ErrorType.REQUESTER_ALREADY_COUPLED);
        }

        Long receiverId = reverseRedisTemplate.opsForValue().get(inputCode);
        if(receiverId == null) {
            throw new RestApiException(ErrorType.LUBEE_CODE_NOT_FOUND);
        }

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));

        if (receiver.isAlreadyCouple()) {
            throw new RestApiException(ErrorType.RECEIVER_ALREADY_COUPLED);
        }

        Couple couple = Couple.builder()
                .receiver(receiver)
                .requester(requester)
                .build();

        coupleRepository.save(couple);

        requester.linkCouple(couple);
        receiver.linkCouple(couple);
        userRepository.save(requester);
        userRepository.save(receiver);

        redisTemplate.delete(receiver.getId());         // 커플된 유저의 러비코드는 삭제하기
        redisTemplate.delete(requester.getId());        // 만약 해당 key가 없어도 무시됨

        return ResponseUtils.ok(couple.getId(), ErrorResponse.builder().status(200).message("커플 생성 성공").build());
    }

}
