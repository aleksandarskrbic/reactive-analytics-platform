package com.github.aleksandarskrbic.adapter.postgres;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import com.github.aleksandarskrbic.adapter.http.dto.CreateLocation;
import com.github.aleksandarskrbic.domain.model.Location;
import com.github.aleksandarskrbic.domain.repository.LocationRepository;

@ApplicationScoped
public class PostgresLocationRepository implements LocationRepository {

    private static final String INSERT_QUERY = "INSERT INTO locations (name, description) VALUES ($1, $2) RETURNING (id)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM locations WHERE id = $1";
    private static final String FIND_ALL_QUERY = "SELECT * FROM locations";
    private static final String UPDATE_QUERY = "UPDATE locations * SET name = $1, description = $2 WHERE id = $3";
    private static final String DELETE_QUERY = "DELETE FROM fruits WHERE id = $1";

    @Inject
    private PgPool pgClient;

    @Inject
    private LocationMapper mapper;

    @Override
    public Uni<Optional<Long>> save(final CreateLocation location) {
        return pgClient.preparedQuery(INSERT_QUERY)
            .execute(Tuple.of(location.getName(), location.getDescription()))
            .onItem().apply(rows -> rows.iterator().next().getLong("id"))
            .onItem().apply(Optional::ofNullable);
    }

    @Override
    public Uni<Optional<Location>> findById(final Long id) {
        return pgClient.preparedQuery(FIND_BY_ID_QUERY)
            .execute(Tuple.of(id))
            .onItem().apply(RowSet::iterator)
            .onItem().apply(iterator -> iterator.hasNext()
                ? Optional.of(mapper.fromRow(iterator.next()))
                : Optional.empty());
    }

    @Override
    public Multi<Location> findAll() {
        return pgClient.query(FIND_ALL_QUERY)
            .execute()
            .onItem().produceMulti(rows -> Multi.createFrom().iterable(rows))
            .onItem().apply(mapper::fromRow);
    }

    @Override
    public Uni<Boolean> update(final Long id, final CreateLocation location) {
        return pgClient.preparedQuery(UPDATE_QUERY)
            .execute(Tuple.of(location.getName(), location.getDescription(), id))
            .onItem().apply(rows -> rows.rowCount() == 1);
    }

    @Override
    public Uni<Boolean> delete(final Long id) {
        return pgClient.preparedQuery(DELETE_QUERY)
            .execute(Tuple.of(id))
            .onItem().apply(rows -> rows.rowCount() == 1);
    }
}
