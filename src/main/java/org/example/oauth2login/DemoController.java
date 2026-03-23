package org.example.oauth2login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String get(@AuthenticationPrincipal OAuth2User user) {
        log.info(user.toString());
        String username = user.getAttribute("login");
        String avatar = user.getAttribute("avatar_url");

        return """
        <html>
            <head>
                <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono&display=swap" rel="stylesheet">
            </head>
            <body style="
                margin:0;
                font-family: 'JetBrains Mono', monospace;
                background:#0d1117;
                color:white;
            ">
                <div style="
                    display:flex;
                    justify-content:center;
                    align-items:center;
                    height:100vh;
                ">
                    <div style="
                        display:flex;
                        flex-direction:column;
                        align-items:center;
                        gap:15px;
                    ">
                        <img src="%s" width="120" style="border-radius:50%%;" />
                        <h1>Welcome %s</h1>
                    </div>
                </div>
            </body>
        </html>
        """.formatted(avatar, username);
    }

}
