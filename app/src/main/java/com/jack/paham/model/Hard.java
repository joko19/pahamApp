package com.jack.paham.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hard implements Parcelable {
    private int num, img, question, answer, score;
    private String aPilih, bPilih, cPilih, dPilih;

    public Hard() {
    }

    public Hard(int num, int img, int question, int answer, int score, String aPilih, String bPilih, String cPilih, String dPilih) {
        this.num = num;
        this.img = img;
        this.question = question;
        this.answer = answer;
        this.score = score;
        this.aPilih = aPilih;
        this.bPilih = bPilih;
        this.cPilih = cPilih;
        this.dPilih = dPilih;
    }

    protected Hard(Parcel in) {
        num = in.readInt();
        img = in.readInt();
        question = in.readInt();
        answer = in.readInt();
        score = in.readInt();
        aPilih = in.readString();
        bPilih = in.readString();
        cPilih = in.readString();
        dPilih = in.readString();
    }

    public static final Creator<Hard> CREATOR = new Creator<Hard>() {
        @Override
        public Hard createFromParcel(Parcel in) {
            return new Hard(in);
        }

        @Override
        public Hard[] newArray(int size) {
            return new Hard[size];
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

    public String getaPilih() {
        return aPilih;
    }

    public void setaPilih(String aPilih) {
        this.aPilih = aPilih;
    }

    public String getbPilih() {
        return bPilih;
    }

    public void setbPilih(String bPilih) {
        this.bPilih = bPilih;
    }

    public String getcPilih() {
        return cPilih;
    }

    public void setcPilih(String cPilih) {
        this.cPilih = cPilih;
    }

    public String getdPilih() {
        return dPilih;
    }

    public void setdPilih(String dPilih) {
        this.dPilih = dPilih;
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
        dest.writeString(aPilih);
        dest.writeString(bPilih);
        dest.writeString(cPilih);
        dest.writeString(dPilih);
    }
}
