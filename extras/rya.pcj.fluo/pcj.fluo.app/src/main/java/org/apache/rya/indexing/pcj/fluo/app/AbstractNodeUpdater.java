/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rya.indexing.pcj.fluo.app;

import org.apache.fluo.api.data.Bytes;
import org.apache.rya.api.model.VisibilityBindingSet;
import org.apache.rya.indexing.pcj.fluo.app.util.BindingHashShardingFunction;
import org.apache.rya.indexing.pcj.storage.accumulo.VariableOrder;

/**
 * This class provides the common functionality for generating sharded row
 * keys for any class that extends it.
 *
 */
public abstract class AbstractNodeUpdater {

    /**
     * Generates a sharded row key from the provided arguments for inserting new results
     * into the Fluo table.  The row key is of the form node prefix + shardId + nodeId + BindingSet values.
     * The shardId is formed from a hash of the first Binding value as indicated by the VariableOrder.
     * @param nodeId - query nodeId corresponding to new result
     * @param varOrder - varOrder used to order Binding values for new row key
     * @param bs - BindingSet that values will be taken from to form key
     * @return - sharded row key formed from the provided arguments
     */
    public Bytes makeRowKey(String nodeId, VariableOrder varOrder, VisibilityBindingSet bs) {
        return BindingHashShardingFunction.addShard(nodeId, varOrder, bs);
    }
}
