package com.jack.paham.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int img, num, question, answer;
    private String title;

    public Category() {
    }

    public Category(int img, String title) {
        this.img = img;
        this.title = title;
    }

    protected Category(Parcel in) {
        img = in.readInt();
        num = in.readInt();
        question = in.readInt();
        answer = in.readInt();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeInt(num);
        dest.writeInt(question);
        dest.writeInt(answer);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

}
