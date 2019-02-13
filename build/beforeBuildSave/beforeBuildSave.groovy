/*
 * Copyright (C) 2014 JFrog Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.artifactory.build.DetailedBuildRun

build {
    beforeSave { DetailedBuildRun buildRun ->
        log.debug("Checking if ${buildRun.name} should be modified before" +
                  " saving!")
        buildRun.modules.each { m ->
            m.artifacts.each { a ->
                def type = a.getType()
                if (type.contains(';')) {
                    a.buildArtifact.setType(type.substring(0, type.indexOf(';')))
                }
            }
        }
    }
}
