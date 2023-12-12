package com.example.app_integradora.Modelos;

import com.google.gson.annotations.SerializedName;
import java.util.List;
public class Modelprincipal {


        private List<FeedData> data;

        public List<FeedData> getData() {
            return data;
        }

        public static class FeedData {
            private String feed_key;
            private String value;

            public String getFeedKey() {
                return feed_key;
            }

            public String getValue() {
                return value;
            }
        }

}


