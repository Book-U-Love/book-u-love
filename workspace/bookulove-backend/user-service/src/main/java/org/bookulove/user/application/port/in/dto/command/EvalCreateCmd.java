package org.bookulove.user.application.port.in.dto.command;

import org.bookulove.user.adapter.in.web.dto.request.EvalCreateReq;

public record EvalCreateCmd(

        Long revieweeId,

        int grade,

        String content

) {
    public static EvalCreateCmd of(EvalCreateReq req) {
        return new EvalCreateCmd(req.revieweeId(), req.grade(), req.content());
    }
}
