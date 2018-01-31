package com.ef.parser;

public enum DurationType {

    HOURLY("hourly"), DAILY("daily");

    private String descricaoDurationType;

    DurationType(String descricaoDurationType) {
        this.descricaoDurationType = descricaoDurationType;
    }

    public String getDescricaoDurationType() {
        return descricaoDurationType;
    }

    public void setDescricaoDurationType(String descricaoDurationType) {
        this.descricaoDurationType = descricaoDurationType;
    }

    public static DurationType getDurationType(String durationType) {
        if (HOURLY.getDescricaoDurationType().equals(durationType)) {
            return HOURLY;
        } else if (DAILY.getDescricaoDurationType().equals(durationType)) {
            return DAILY;
        }

        return null;
    }
}
