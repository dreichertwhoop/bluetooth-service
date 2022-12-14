package com.whoop.template.data;

import com.hubspot.rosetta.jdbi3.BindWithRosetta;
import com.whoop.template.models.TemplateModel;
import com.whoop.template.models.TemplateModelEgg;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Template DAO showing CRUD operations in our typical pattern.
 */
public interface TemplateModelDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO template_modal (name)")
    TemplateModel insertTemplateModel(@BindWithRosetta TemplateModelEgg model);

    @SqlQuery("SELECT * FROM template_modal WHERE id = :id AND DELETED = false")
    Optional<TemplateModel> getTemplateModelById(@Bind("id") UUID id);

    @SqlQuery("SELECT * FROM template_modal WHERE name = :name AND DELETED = false")
    Optional<TemplateModel> getTemplateModelByName(@Bind("name") String name);

    @SqlQuery("SELECT * FROM template_modal WHERE DELETED = false ORDER BY id DESC OFFSET :offset LIMIT limit")
    List<TemplateModel> getAllTemplateModels(@Bind("offset") int offset, @Bind("limit") int limit);

    @GetGeneratedKeys
    @SqlUpdate("UPDATE template_modal SET (name = :name, updated_at=now()) WHERE id = :id")
    TemplateModel updateTemplateModel(@Bind("id") UUID id, @BindWithRosetta TemplateModelEgg model);

    @SqlUpdate("UPDATE template_modal SET deleted = TRUE WHERE id = :id")
    void deleteTemplateModel(@Bind("id") UUID id);



}
