package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.message.ErrorMessageJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class JsonResponse {

    protected Object result;

    protected JsonResponseMetadata meta;

    protected List<ErrorMessageJson> errors = new ArrayList<>();

    public void setErrors(List<ErrorMessageJson> errors) {
        Optional.ofNullable(errors).orElseGet(ArrayList::new).forEach(this::addError);
    }

    public void addError(ErrorMessageJson error) {
        this.errors.add(error);
    }

}
