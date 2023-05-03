package com.yu.chatliteserver.request.painting;

import lombok.Data;

@Data
public class PaintingRequest {
    private String prompt;
    private Long n;
    private String size;
}
