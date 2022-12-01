package com.example.demo.Entity;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class Ride extends BaseEntity {
    @Column(name = "from_", columnDefinition = "geometry(Point, 4326)")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @Type(type = "jts_geometry")
    private Point from;

    @Column(name = "to_", columnDefinition = "geometry(Point, 4326)")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @Type(type = "jts_geometry")
    private Point to;

    @Column(name = "date_")
    private Date date;
}
