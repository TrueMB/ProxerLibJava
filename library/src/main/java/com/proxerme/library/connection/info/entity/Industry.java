package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CountryParameter.Country;
import com.proxerme.library.parameters.IndustryTypeParameter.IndustryType;
import com.squareup.moshi.Json;

/**
 * An entity which holds the info of a single industry.
 *
 * @author Ruben Gees
 */
public class Industry implements IdItem, Parcelable {

    public static final Creator<Industry> CREATOR = new Creator<Industry>() {
        @Override
        public Industry createFromParcel(Parcel source) {
            return new Industry(source);
        }

        @Override
        public Industry[] newArray(int size) {
            return new Industry[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "type")
    private String type;
    @Json(name = "country")
    private String country;
    @Json(name = "link")
    private String link;
    @Json(name = "description")
    private String description;

    /**
     * The constructor.
     *
     * @param id          The id.
     * @param name        The name.
     * @param type        The type of the industry.
     * @param country     The country the industry resides in.
     * @param link        The link.
     * @param description A description of the industry.
     */
    public Industry(@NonNull String id, @NonNull String name, @NonNull @IndustryType String type,
                    @NonNull @Country String country, @NonNull String link,
                    @NonNull String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
        this.link = link;
        this.description = description;
    }

    protected Industry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.country = in.readString();
        this.link = in.readString();
        this.description = in.readString();
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the type.
     *
     * @return The type.
     */
    @NonNull
    @IndustryType
    public String getType() {
        return type;
    }

    /**
     * Returns the country.
     *
     * @return The country.
     */
    @NonNull
    @Country
    public String getCountry() {
        return country;
    }

    /**
     * Returns the link to the homepage.
     *
     * @return The link.
     */
    @NonNull
    public String getLink() {
        return link;
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Industry industry = (Industry) o;

        if (!id.equals(industry.id)) return false;
        if (!name.equals(industry.name)) return false;
        if (!type.equals(industry.type)) return false;
        if (!country.equals(industry.country)) return false;
        if (!link.equals(industry.link)) return false;
        return description.equals(industry.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.country);
        dest.writeString(this.link);
        dest.writeString(this.description);
    }
}