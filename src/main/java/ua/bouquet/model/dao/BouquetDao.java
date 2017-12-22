package ua.bouquet.model.dao;

import ua.bouquet.model.entity.AbstractPlant;
import ua.bouquet.model.entity.Bouquet;

public interface BouquetDao extends GenericDao<Bouquet> {
    Bouquet findByAbstractPlant(AbstractPlant abstractPlant);
}
