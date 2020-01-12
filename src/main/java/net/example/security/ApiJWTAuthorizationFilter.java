package net.example.security;

import net.example.util.JwtUltis;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Claims;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {

    ApiJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Kiểm tra token
        Claims claims = JwtUltis.verifyToken(request);
        if (claims == null) {
            chain.doFilter(request, response);
            return;
        }

        // Thêm object Authentication vào SecurityContext
        // Controller có thể lấy ra thông tin user đang đăng nhập từ đây để sử dụng
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(Claims claims) {
        // Lấy thông tin username
        String user = claims.getSubject();
        // Lấy thông tin role
        ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
        // Convert mảng role thành mảng GrantedAuthority để authentication manager kiểm tra
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
                authorities.add(authority);
            }
        }
        if (user != null) {
            // Trả về đối tượng Authentication chứa thông tin username, thông tin đăng nhập (cấu trúc struct tùy bạn, hiện tại đang để rỗng), tập quyền
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        }
        return null;
    }
}
