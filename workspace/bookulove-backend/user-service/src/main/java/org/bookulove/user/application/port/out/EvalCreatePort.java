package org.bookulove.user.application.port.out;

public interface EvalCreatePort {

    void createEval(int grade, String content, Long reviewerId, Long revieweeId);
}
