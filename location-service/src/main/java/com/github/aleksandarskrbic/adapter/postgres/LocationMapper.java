package com.github.aleksandarskrbic.adapter.postgres;

import javax.enterprise.context.ApplicationScoped;
import com.github.aleksandarskrbic.domain.model.Location;
import io.vertx.mutiny.sqlclient.Row;

@ApplicationScoped
public class LocationMapper {

    public Location fromRow(final Row row) {
        return new Location(
            row.getLong("id"),
            row.getString("name"),
            row.getString("description")
        );
    }
}
