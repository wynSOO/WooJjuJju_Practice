package app.com.woo_page.service;

import android.content.Context;

import app.com.woo_page.R;

public class WooDao {
    private String[] title_preg;
    private String[] title_baby;

    Context context;

    public WooDao(Context context) {
        this.context = context;
    }

    public void makeTitle(){

        title_preg = context.getResources().getStringArray(R.array.title_preg);
        title_baby = context.getResources().getStringArray(R.array.title_baby);

    }

    public String[] getTitle_preg() {
        return title_preg;
    }

    public String[] getTitle_baby() {
        return title_baby;
    }
}
