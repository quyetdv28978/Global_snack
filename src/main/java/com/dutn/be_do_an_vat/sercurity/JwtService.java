package com.dutn.be_do_an_vat.sercurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; //Key bảo mật
    public static final int TOKEN_EXPIRATION = 30 * 60 * 1000; // Thời gian hết hạn: 30 phút sau khi tạo

    /**
     * Tạo ra JWT từ thông tin user
     * Trả về từ phương thức là chuỗi JWT đã được tạo, chứa thông tin về người dùng và các
     * thông tin thời gian (phát hành, hết hạn)
     * */
    public String generateToken(String username){
        //Thời gian
        Date dateNow = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + this.TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(username) //Xác định subject của JWT -> username
                .setIssuedAt(dateNow) // Xác định thời gian mà JWT được tạo
                .setExpiration(expirationDate) // Xác định thời điểm hết hạn
                .signWith(getSignWithKey(), SignatureAlgorithm.HS256) //Chọn thuật thoát để ký và xác thực JWT
                .compact();
    }

    /**
     * Tạo ra một khóa để sử dụng trong việc ký (sign) chuỗi JWT
     * bằng thuật toán HMAC-SHA256
     * */
    private Key getSignWithKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     *
     * @param token
     * @return Lấy ra danh sách cách Claims từ tokem
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignWithKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * trả về 1 Claim
     * @param token
     * @param claimsResolver dạng của claim
     * @return Claim
     * @param <T>
     */
    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Kiểm tra hạn của token
     * @param token
     * @return còn true, hết false
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    /**
     * lấy username
     * @param token
     * @return username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Kiểm tra xem token hợp lệ không
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))
                && !isTokenExpired(token);
    }

}
