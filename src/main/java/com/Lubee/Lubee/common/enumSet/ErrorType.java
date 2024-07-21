package com.Lubee.Lubee.common.enumSet;

import lombok.Getter;

/*
    정보 응답 100 continue 상태가 괜찮으며 클라이언트가 계쏙해서 요청하거나 요청이 완료된 경우에는 무시해도 괜찮음
    정보 응답 101 SWITCH_PROTOCOL 클라이언트가 보낸 UPGRADE 요청헤더에 대한 응답으로, 서버에서 프로토콜을 변경할 것

    성공 응답 200 OK 요청이 성공적으로 완료
    성공 응답 201 CREATED 요청이 성공적이었으며 그 결과로 새로운 리소스가 생성
    성공 응답 202 ACCEPTED 요청을 수신했지만 그에 응하여 행동할 수 없다

    리다이렉션 메시지 300 MULTIPLE_CHOICE 요청에 대해 하나 이상의 응답이 가능
    리다이렉션 메시지 301 MOVED_PERMANETLY 요청한 리소스의 URI 가 변경되었다는 의미
    리다이렉션 메시지 302 FOUND 요청한 리소스의 URI가 일시적으로 변경
    리다이렉션 메시지 303 SEE_OTHER 클라이언트가 요청한 리스소를 다른 URI에서 GET 요청을 통해 얻어야할 경우 서버가 클라이언트로 직접 보내는 응답

    클라이언트 오류 응답 400 BAD_REQUEST 잘못된 문법으로 인해 서버가 요청을 이해할 수 없을 때
    클라이언트 오류 응답 401 UNAUTHORIZED 인증되지 않았다는 의미
    클라이언트 오류 응답 403 FORBIDDEN 클라이언트가 콘텐츠에 접근할 권리를 가지고 있지 않다는 의미
    클라이언트 오류 응답 404 NOT_FOUND 서버는 요청받은 리소스를 찾을 수 없다

    서버 오류 응답 500 INTERVAL_SERVER_ERROR 처리할 수 없는 내부 오류가 발생
    서버 오류 응답 501 NOT_IMPLEMENTED 요청 메서드는 서버가 지원하지 않거나 처리할 수 없다
    서버 오류 응답 503 SERVICE_UNAVAILABLE 서버는 요청을 처리할 준비가 되지 않았다
 */


@Getter
public enum ErrorType {
    ALREADY_EXIST(400,"해당 아이디는 사용할 수 없습니다"),
    NOT_VALID_TOKEN(400, "토큰이 유효하지 않습니다."),
    NOT_FOUND_MEMORY(404, "해당 MEMORY가 존재하지 않습니다"),
    DUPLICATED_USERNAME(400, "중복된 username 입니다."),
    NOT_MATCHING_INFO(404, "회원을 찾을 수 없습니다."),
    NOT_MATCHING_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER(404, "사용자가 존재하지 않습니다."),
    NOT_FOUND(404,"해당 데이터가 존재하지 않습니다" ),
    LOGIN_FAIL(400, "로그인에 실패하였습니다. 아이디 OR 비밀번호를 확인해주세요"),
    REFRESH_TOKEN_NOT_VALIDATE(404, "Refresh 토큰이 존재하지 않거나 만료되었습니다."),
    ACCESS_TOKEN_NOT_EXPIRED(404, "엑세스 토큰이 존재하지 않거나 만료되었습니다."),
    LUBEE_CODE_NOT_FOUND(404, "Lubee code가 존재하지 않거나 만료되었습니다."),
    REQUESTER_ALREADY_COUPLED(400, "커플 신청자가 이미 커플입니다."),
    RECEIVER_ALREADY_COUPLED(400, "커플 요청받은 사람이 이미 커플입니다."),
    NOT_FOUND_COUPLE(404, "해당 커플 정보를 찾을 수 없습니다"),
    NOT_FOUND_CALENDAR(404, "달력이 존재하지 않습니다."),
    NOT_FOUND_DATE_COMMENT(404, "데이트코멘트가 존재하지 않습니다."),
    NOT_MATCHING_USER(403, "유저 정보가 알맞지 않습니다."),
    NOT_MATCHING_COUPLE(403, "커플 정보가 알맞지 않습니다."),
    NOT_MATCHING_CALENDAR(403, "달력 정보가 알맞지 않습니다."),
    INSUFFICIENT_COMMENTS(403, "두 명의 구성원이 모두 데이트 코멘트를 작성해야 열람할 수 있습니다."),
    NOT_FOUND_COUPLE_DATE_COMMENT(404, "커플 모두 데이트코멘트를 작성하지 않았습니다."),
    NOT_FOUND_LOCATION(404, "해당 Location 데이터가 존재하지 않습니다."),
    NOT_FOUND_USERMEMORYREACTION(404, "해당 USERMEMORYREACTION 데이터가 존재하지 않습니다."),
    PARSING_ERROR(404, "DATE <-> STRING PARSING 에러입니다"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR 입니다"),
    FILE_UPLOAD_FAILED(400, "이미지 업로드에 실패하였습니다"),
    ;


    private int code;
    private String message;

    //  Error Type 생성자 생성
    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
