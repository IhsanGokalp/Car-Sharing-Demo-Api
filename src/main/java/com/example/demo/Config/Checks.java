package com.example.demo.Config;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import javax.persistence.EntityNotFoundException;

public final class Checks {
    private Checks() {
        super();
    }

    public final static <T> T checkEntityExists(T entity, String message) {
        if (entity == null) {
            throw new EntityNotFoundException(message);
        }
        return entity;
    }

    public static Geometry wktToGeometry(String message) throws ParseException {
        return new WKTReader().read(message);
    }
}