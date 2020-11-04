package com.mall.cloud.model.result.sytem;

import com.mall.cloud.common.persistence.result.BaseResult;
import lombok.*;

/**
 * <p>封装Qicloud项目SequenceIdResult类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-30 00:49
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SequenceIdResult extends BaseResult {
    private String id;


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Result{");
        builder.append("id=").append(id);
        builder.append(", status=").append(Boolean.TRUE);
        builder.append('}');
        return builder.toString();
    }
}
