package net.mikoto.roxy.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceConfig {
    private String resourceName;
    private String algorithmName;
    private InstantiableObject[] algorithmParams;
}