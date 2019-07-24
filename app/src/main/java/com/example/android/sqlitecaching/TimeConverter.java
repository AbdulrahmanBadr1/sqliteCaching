package com.example.android.sqlitecaching;

//use it like that
// ProfitModel.Profit profitModel = new ProfitModel().setEnd_time("").Build();


class TimeConverter {
    //vars
    private String currentDate, updateDate, year, month, day, hour, miniute;

    public TimeConverter setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
        return this;

    }

    public TimeConverter setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;

    }

    public TimeConverter setYear(String year) {
        this.year = year;
        return this;

    }

    public TimeConverter setMonth(String month) {
        this.month = month;
        return this;

    }

    public TimeConverter setDay(String day) {
        this.day = day;
        return this;

    }

    public TimeConverter setHour(String hour) {
        this.hour = hour;
        return this;

    }

    public TimeConverter setMiniute(String miniute) {
        this.miniute = miniute;
        return this;

    }


    TimeConverter Build() {
        return new TimeConverterModel( currentDate,  updateDate,  year,  month,  day,  hour,  miniute);
    }

    class TimeConverterModel extends TimeConverter {
        //vars
        private String currentDate, updateDate, year, month, day, hour, miniute;

        public TimeConverterModel(String currentDate, String updateDate, String year, String month, String day, String hour, String miniute) {
            this.currentDate = currentDate;
            this.updateDate = updateDate;
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.miniute = miniute;
        }
    }
}
