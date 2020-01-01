package com.jack.paham.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Difficult implements Parcelable {

    private String adiff, bdiff, cdiff, ddiff;
    private int num, answer, question, img, score, vidAnswer;

    public Difficult() {
    }

    protected Difficult(Parcel in) {
        adiff = in.readString();
        bdiff = in.readString();
        cdiff = in.readString();
        ddiff = in.readString();
        num = in.readInt();
        answer = in.readInt();
        question = in.readInt();
        img = in.readInt();
        score = in.readInt();
        vidAnswer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adiff);
        dest.writeString(bdiff);
        dest.writeString(cdiff);
        dest.writeString(ddiff);
        dest.writeInt(num);
        dest.writeInt(answer);
        dest.writeInt(question);
        dest.writeInt(img);
        dest.writeInt(score);
        dest.writeInt(vidAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Difficult> CREATOR = new Creator<Difficult>() {
        @Override
        public Difficult createFromParcel(Parcel in) {
            return new Difficult(in);
        }

        @Override
        public Difficult[] newArray(int size) {
            return new Difficult[size];
        }
    };

    public String getAdiff() {
        return adiff;
    }

    public void setAdiff(String adiff) {
        this.adiff = adiff;
    }

    public String getBdiff() {
        return bdiff;
    }

    public void setBdiff(String bdiff) {
        this.bdiff = bdiff;
    }

    public String getCdiff() {
        return cdiff;
    }

    public void setCdiff(String cdiff) {
        this.cdiff = cdiff;
    }

    public String getDdiff() {
        return ddiff;
    }

    public void setDdiff(String ddiff) {
        this.ddiff = ddiff;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVidAnswer() {
        return vidAnswer;
    }

    public void setVidAnswer(int vidAnswer) {
        this.vidAnswer = vidAnswer;
    }

}
