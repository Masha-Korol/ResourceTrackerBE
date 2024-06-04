package com.example.concert_service.data.dto.resource;

import java.util.Objects;

public class ResourceInfoDto {

    private Integer artistId;
    private String artistName;

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceInfoDto that = (ResourceInfoDto) o;
        return Objects.equals(artistName, that.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, artistName);
    }

    @Override
    public String toString() {
        return "ArtistInfoDto{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
