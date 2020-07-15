package com.YmZxf.community.HelloController;


import com.YmZxf.community.dto.AccessTokenDto;
import com.YmZxf.community.dto.GithubUser;
import com.YmZxf.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    public AuthorizeController() {
    }

    @GetMapping("/callback")
    public String CallBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String string = gitHubProvider.getAccessToken(accessTokenDto);
        System.out.println(string);
        GithubUser githubUser = gitHubProvider.getUser(string);
        System.out.println(githubUser.getId());
        return "index";
    }

}
