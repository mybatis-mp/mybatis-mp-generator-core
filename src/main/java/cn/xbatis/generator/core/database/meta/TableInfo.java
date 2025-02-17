/*
 *  Copyright (c) 2024-2025, Ai东 (abc-127@live.cn).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License").
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package cn.xbatis.generator.core.database.meta;

import lombok.Data;

import java.util.List;

@Data
public class TableInfo {

    private String schema;

    private String catalog;

    private String name;

    private String remarks;

    private String tableType;

    private ColumnInfo idColumnInfo;

    private List<ColumnInfo> idColumnInfoList;

    private List<ColumnInfo> columnInfoList;

    public boolean isView() {
        return "VIEW".equals(tableType);
    }

}
