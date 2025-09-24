package it.back.back_app.filter;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import it.back.back_app.common.utils.JWTUtils;
import it.back.back_app.security.dto.UserSecureDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * 로그인 이전에 JWT를 검증하기 위한 필터
 * 검증에 문제가 없다면 인증정보를 SecurityContextHoler에 저장
 */
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 요청 request header에서 token 찾기
        // Authrization 헤더에 원래 있는 속성. 보안키 보낼 때 사용
        String accessToken = request.getHeader("Authorization");

        // 토큰이 없는 경우
        if(accessToken == null) {
            log.info("accessToken is null");
            // doFilter > 없는 경우 다음 Filter로 보냄
            filterChain.doFilter(request, response);
            return; // 자격이 없으니 로그인으로 넘어감
        }

        try {
            // 베리어 토큰 만들기
            if(accessToken.startsWith("Bearer ")) {
                accessToken = accessToken.substring(7);

                if(!jwtUtils.validateToken(accessToken)) {
                    throw new IllegalAccessException("유효하지 않은 토큰입니다.");
                }
            }
            
            // 토큰의 카테고리 검색
            String category = jwtUtils.getCategory(accessToken);

            if(! category.equals("access")) {
                throw new IllegalAccessException("유효하지 않은 토큰입니다.");
            }

        } catch(Exception e) {
            // response 한다
            PrintWriter writer = response.getWriter();
            writer.println("토큰일 유효하지 않습니다.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return; // 함수 종료
        }

        // 인증 성공
        String userId = jwtUtils.getUserId(accessToken);
        String userName = jwtUtils.getUserName(accessToken);
        String userRole = jwtUtils.getUserRole(accessToken);

        UserSecureDTO dto = new UserSecureDTO(userId, userName, "", userRole);

        // 시큐리티
        Authentication authentication = new UsernamePasswordAuthenticationToken(dto, null, dto.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음으로 이동
        filterChain.doFilter(request, response);

    }
}
