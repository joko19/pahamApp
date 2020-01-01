package com.jack.paham.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Medium implements Parcelable {
    private int num, img, question, answer, score;
    private String aChoise, bChoise, cChoise;

    public Medium() {
    }

    protected Medium(Parcel in) {
        num = in.readInt();
        img = in.readInt();
        question = in.readInt();
        answer = in.readInt();
        score = in.readInt();
        aChoise = in.readString();
        bChoise = in.readString();
        cChoise = in.readString();
    }

    public static final Creator<Medium> CREATOR = new Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel in) {
            return new Medium(in);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getaChoise() {
        return aChoise;
    }

    public void setaChoise(String aChoise) {
        this.aChoise = aChoise;
    }

    public String getbChoise() {
        return bChoise;
    }

    public void setbChoise(String bChoise) {
        this.bChoise = bChoise;
    }

    public String getcChoise() {
        return cChoise;
    }

    public void setcChoise(String cChoise) {
        this.cChoise = cChoise;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeInt(img);
        dest.writeInt(question);
        dest.writeInt(answer);
        dest.writeInt(score);
        dest.writeString(aChoise);
        dest.writeString(bChoise);
        dest.writeString(cChoise);
    }
}
