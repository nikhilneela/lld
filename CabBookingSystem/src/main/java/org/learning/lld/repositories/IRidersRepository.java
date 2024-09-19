package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Rider;

import java.util.List;

public interface IRidersRepository {
    public void registerRider(@NonNull final Rider rider);
}
