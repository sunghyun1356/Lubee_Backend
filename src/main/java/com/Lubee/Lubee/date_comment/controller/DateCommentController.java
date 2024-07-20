package com.Lubee.Lubee.date_comment.controller;

import com.Lubee.Lubee.common.api.ApiResponseDto;
import com.Lubee.Lubee.common.api.SuccessResponse;
import com.Lubee.Lubee.date_comment.dto.CreateDateCommentRequest;
import com.Lubee.Lubee.date_comment.dto.DateCommentResponse;
import com.Lubee.Lubee.date_comment.dto.TodayCoupleDateCommentRequest;
import com.Lubee.Lubee.date_comment.dto.UpdateDateCommentRequest;
import com.Lubee.Lubee.date_comment.service.DateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/datecomments")
public class DateCommentController {

    private final DateCommentService dateCommentService;

    /**
     * 데이트코멘트 작성
     *
     * @param userDetails 인증된 사용자의 정보를 담고 있는 UserDetails 객체
     * @param createDateCommentRequest 데이트코멘트 생성 요청 Dto (content, coupleId, date)
     * @return ApiResponseDto<Long>  생성된 Datecomment의 id를 포함
     */
    @PostMapping("/")
    public ApiResponseDto<Long> createDateComment(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody final CreateDateCommentRequest createDateCommentRequest){

        return dateCommentService.createDateComment(userDetails, createDateCommentRequest);
    }

    /**
     * 데이트코멘트 조회 by Datecomment's id
     *
     * @param id 데이트코멘트의 id
     * @return ApiResponseDto<DateCommentResponse>
     */
    /// 이거
    @GetMapping("/{id}")
    public ApiResponseDto<DateCommentResponse> findDateComment(@PathVariable final Long id){

        return dateCommentService.findDateComment(id);
    }

    /**
     * 커플의 데이트코멘트 조회 (날짜 하루)
     *
     * @param userDetails 인증된 사용자의 정보를 담고 있는 UserDetails 객체
     * @param todayCoupleDateCommentRequest 해당 날짜에 작성된 커플의 데이트코멘트 요청 dto (userId, coupleId, date)
     * @return List<DateCommentResponse>
     */
    @PostMapping("/today")
    public ApiResponseDto<List<DateCommentResponse>> findTodayDateCommentByCouple(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody final TodayCoupleDateCommentRequest todayCoupleDateCommentRequest){

        return dateCommentService.findTodayDateCommentByCouple(userDetails, todayCoupleDateCommentRequest);
    }

    /**
     * 데이트코멘트 수정
     *
     * @param userDetails 인증된 사용자의 정보를 담고 있는 UserDetails 객체
     * @param updateDateCommentRequest 데이트코멘트 내용 수정 변경 요청 dto (dateCommentId, content)
     * @return SuccessResponse
     */
    @PutMapping("/")
    public SuccessResponse updateContent(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateDateCommentRequest updateDateCommentRequest){

        return dateCommentService.changeContent(userDetails, updateDateCommentRequest);
    }

}
