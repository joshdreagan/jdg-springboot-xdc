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
import org.infinispan.configuration.cache.BackupConfiguration;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.remoting.transport.jgroups.JGroupsTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InfinispanConfiguration {

  /*
  @Bean
  GlobalConfiguration globalCacheConfiguration() {
    return new GlobalConfigurationBuilder()
            .globalJmxStatistics()
              .enable()
            .transport()
              .defaultTransport()
              .clusterName("cluster")
              .machineId("machine-1")
              .rackId("rack-1")
              .siteId("site-1")
              .addProperty(JGroupsTransport.CONFIGURATION_FILE, "jgroups.xml")
            .build();
  }

  @Bean
  Configuration cacheConfiguration() {
    return new ConfigurationBuilder()
            .jmxStatistics()
              .enable()
            .clustering()
              .cacheMode(CacheMode.DIST_SYNC)
              .hash()
                .numOwners(2)
              .sites()
                .addBackup()
                   .site("SITE1")
                   .strategy(BackupConfiguration.BackupStrategy.ASYNC)
                   .enabled(true)
              .sites()
                .addBackup()
                   .site("SITE2")
                   .strategy(BackupConfiguration.BackupStrategy.ASYNC)
                   .enabled(true)
            .build();
  }
  */
  
  @Bean
  //EmbeddedCacheManager cacheManager(GlobalConfiguration globalConfiguration, Configuration configuration) throws IOException {
    //return new DefaultCacheManager(globalConfiguration, configuration);
  EmbeddedCacheManager cacheManager() throws IOException {
    return new DefaultCacheManager("infinispan.xml");
  }
}
