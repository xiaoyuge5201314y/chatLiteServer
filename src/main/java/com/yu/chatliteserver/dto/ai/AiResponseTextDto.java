package com.yu.chatliteserver.dto.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class AiResponseTextDto {
    private String role;
    private String content;

    public AiResponseTextDto(String str) {
        Pattern pattern = Pattern.compile("\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String match = matcher.group(1);
            System.out.println(match);
            this.role = ((match.split(",")[0]).split(":")[1]).trim();
            this.content = ((match.split(",")[1]).split(":")[1]).trim();
        }
    }
}
