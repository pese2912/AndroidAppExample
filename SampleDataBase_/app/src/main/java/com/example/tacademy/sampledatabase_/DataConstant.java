package com.example.tacademy.sampledatabase_;

import android.provider.BaseColumns;

/**
 * Created by Tacademy on 2016-04-29.
 */
public class DataConstant { //db컬럼명 상수로 지정

    public interface PersonTable extends BaseColumns{


        public static final String TABLE_NAME= "person_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_OFFICE ="office";

    }
}
