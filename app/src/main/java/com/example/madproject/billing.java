package com.example.madproject;

    public class billing {
        String Id;
        String billnum;
        String item;
        String qty;
        String price;

        public billing() {
        }

        public billing(String billnum,String item, String qty, String price) {
            this.billnum = billnum;
            this.item = item;
            this.qty = qty;
            this.price = price;
        }

        public String getBillnum() {
            return billnum;
        }

        public void setBillnum(String billnum) {
            this.billnum = billnum;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            Id = Id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

