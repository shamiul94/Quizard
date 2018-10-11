package com.buetcse.shamiul93.quickquiz;

public class TrueFalse {
    private int questionId;
    private boolean Answer;

    public TrueFalse(int questionId, boolean answer) {
        this.questionId = questionId;
        Answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getAnswer() {
        return Answer;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }
}
