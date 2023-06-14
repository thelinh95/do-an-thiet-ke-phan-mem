package com.baouyen.doan.dto;

public class StoreDto {
    private Long id;
    private String name;
    private String onlineAddress;
    private String offlineAddress;

    private Double latitude;
    private Double longitude;
    private StoreType type;
    public enum StoreType {
        ONLINE("online"),
        OFFLINE("offline"),
        ;

        private String code;

        StoreType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
    }

    public String getOfflineAddress() {
        return offlineAddress;
    }

    public void setOfflineAddress(String offlineAddress) {
        this.offlineAddress = offlineAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
