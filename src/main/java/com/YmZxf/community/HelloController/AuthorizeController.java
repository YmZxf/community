package com.YmZxf.community.HelloController;


import com.YmZxf.community.dto.AccessTokenDto;
import com.YmZxf.community.dto.GithubUser;
import com.YmZxf.community.mapper.UserMapper;
import com.YmZxf.community.model.User;
import com.YmZxf.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private UserMapper userMapper;
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
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String string = gitHubProvider.getAccessToken(accessTokenDto);
        System.out.println(string);
        GithubUser githubUser = gitHubProvider.getUser(string);
        if(githubUser != null){
            User user = new User();
            user.setId(2);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return"redirect:/";
        }else{
            return"redirect:/";
        }

    }

}
