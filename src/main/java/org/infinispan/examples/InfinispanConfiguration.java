/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.infinispan.examples;

import java.io.IOException;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties({ InfinispanProperties.class, JgroupsProperties.class })
public class InfinispanConfiguration {
  
  /* Don't remove either of these as we need to make sure they're initialized before the cache managers */
  @Autowired(required = true)
  private InfinispanProperties infinispanProperties;
  
  @Autowired(required = true)
  private JgroupsProperties jgroupsProperties;
  /* end block */
  
  @Bean
  EmbeddedCacheManager cacheManager() throws IOException {
    return new DefaultCacheManager(infinispanProperties.getConfigFile());
  }
}
