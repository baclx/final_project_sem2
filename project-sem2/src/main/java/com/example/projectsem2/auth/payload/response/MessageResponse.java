package com.example.projectsem2.auth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String status;
    private String msg;
    private Object data;

    public MessageResponse(String s) {
    }
}
