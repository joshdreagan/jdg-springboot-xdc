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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "infinispan")
public class InfinispanProperties implements InitializingBean {

  private String configFile;
  private String site;
  private String machine;
  private String rack;
  private String nodeName;
  private Integer numOwners;

  public String getConfigFile() {
    return configFile;
  }

  public void setConfigFile(String configFile) {
    this.configFile = configFile;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getMachine() {
    return machine;
  }

  public void setMachine(String machine) {
    this.machine = machine;
  }

  public String getRack() {
    return rack;
  }

  public void setRack(String rack) {
    this.rack = rack;
  }

  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public int getNumOwners() {
    return numOwners;
  }

  public void setNumOwners(int numOwners) {
    this.numOwners = numOwners;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (configFile != null) System.setProperty("infinispan.config_file", configFile);
    if (site != null) System.setProperty("infinispan.site", site);
    if (machine != null) System.setProperty("infinispan.machine", machine);
    if (rack != null) System.setProperty("infinispan.rack", rack);
    if (nodeName != null) System.setProperty("infinispan.node_name", nodeName);
    if (numOwners != null) System.setProperty("infinispan.num_owners", String.valueOf(numOwners));
  }
}
