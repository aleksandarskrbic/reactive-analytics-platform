package com.github.aleksandarskrbic.domain.repository;

import java.util.Optional;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import com.github.aleksandarskrbic.adapter.http.dto.CreateLocation;
import com.github.aleksandarskrbic.domain.model.Location;

public interface LocationRepository {

    Uni<Optional<Long>> save(final CreateLocation location);

    Uni<Optional<Location>> findById(final Long id);

    Multi<Location> findAll();

    Uni<Boolean> update(final Long id, final CreateLocation location);

    Uni<Boolean> delete(final Long id);
}
