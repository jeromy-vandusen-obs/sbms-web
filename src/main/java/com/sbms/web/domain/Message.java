package com.sbms.web.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Message {
    @NotNull
    @NonNull
    @NotEmpty
    private String language;

    @NotNull
    @NonNull
    @NotEmpty
    private String content;
}
