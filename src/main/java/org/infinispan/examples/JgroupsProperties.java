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

import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jgroups")
public class JgroupsProperties implements InitializingBean {

  private String configFile;
  private TcpProperties tcp;
  private TcppingProperties tcpping;
  private Relay2Properties relay2;

  public String getConfigFile() {
    return configFile;
  }

  public void setConfigFile(String configFile) {
    this.configFile = configFile;
  }

  public TcpProperties getTcp() {
    return tcp;
  }

  public void setTcp(TcpProperties tcp) {
    this.tcp = tcp;
  }

  public TcppingProperties getTcpping() {
    return tcpping;
  }

  public void setTcpping(TcppingProperties tcpping) {
    this.tcpping = tcpping;
  }

  public Relay2Properties getRelay2() {
    return relay2;
  }

  public void setRelay2(Relay2Properties relay2) {
    this.relay2 = relay2;
  }

  public static class TcpProperties {
    
    private String bindAddress;
    private String bindPort;
    private String externalAddress;
    private String externalPort;

    public String getBindAddress() {
      return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
      this.bindAddress = bindAddress;
    }

    public String getBindPort() {
      return bindPort;
    }

    public void setBindPort(String bindPort) {
      this.bindPort = bindPort;
    }

    public String getExternalAddress() {
      return externalAddress;
    }

    public void setExternalAddress(String externalAddress) {
      this.externalAddress = externalAddress;
    }

    public String getExternalPort() {
      return externalPort;
    }

    public void setExternalPort(String externalPort) {
      this.externalPort = externalPort;
    }
  }
  
  public static class TcppingProperties {
    
    private List<String> initialHosts;

    public List<String> getInitialHosts() {
      return initialHosts;
    }

    public void setInitialHosts(List<String> initialHosts) {
      this.initialHosts = initialHosts;
    }
  }
  
  public static class Relay2Properties {
    
    private String configFile;
    private String jgroupsConfigFile;
    private TcpProperties tcp;
    private TcppingProperties tcpping;

    public String getConfigFile() {
      return configFile;
    }

    public void setConfigFile(String configFile) {
      this.configFile = configFile;
    }

    public String getJgroupsConfigFile() {
      return jgroupsConfigFile;
    }

    public void setJgroupsConfigFile(String jgroupsConfigFile) {
      this.jgroupsConfigFile = jgroupsConfigFile;
    }

    public TcpProperties getTcp() {
      return tcp;
    }

    public void setTcp(TcpProperties tcp) {
      this.tcp = tcp;
    }

    public TcppingProperties getTcpping() {
      return tcpping;
    }

    public void setTcpping(TcppingProperties tcpping) {
      this.tcpping = tcpping;
    }
  }
  
  @Override
  public void afterPropertiesSet() throws Exception {
    if (configFile != null) System.setProperty("jgroups.config_file", configFile);
    if (tcp != null && tcp.bindAddress != null) System.setProperty("jgroups.tcp.bind_addr", tcp.bindAddress);
    if (tcp != null && tcp.bindPort != null) System.setProperty("jgroups.tcp.bind_port", tcp.bindPort);
    if (tcp != null && tcp.externalAddress != null) System.setProperty("jgroups.tcp.external_addr", tcp.externalAddress);
    if (tcp != null && tcp.externalPort != null) System.setProperty("jgroups.tcp.external_port", tcp.externalPort);
    if (tcpping != null && tcpping.initialHosts != null) System.setProperty("jgroups.tcpping.initial_hosts", String.join(",", tcpping.initialHosts));
    if (relay2 != null && relay2.configFile != null) System.setProperty("jgroups.relay2.config_file", relay2.configFile);
    if (relay2 != null && relay2.jgroupsConfigFile != null) System.setProperty("jgroups.relay2.jgroups_config_file", relay2.jgroupsConfigFile);
    if (relay2 != null && relay2.tcp != null && relay2.tcp.bindAddress != null) System.setProperty("jgroups.relay2.tcp.bind_addr", relay2.tcp.bindAddress);
    if (relay2 != null && relay2.tcp != null && relay2.tcp.bindPort != null) System.setProperty("jgroups.relay2.tcp.bind_port", relay2.tcp.bindPort);
    if (relay2 != null && relay2.tcp != null && relay2.tcp.externalAddress != null) System.setProperty("jgroups.relay2.tcp.external_addr", relay2.tcp.externalAddress);
    if (relay2 != null && relay2.tcp != null && relay2.tcp.externalPort != null) System.setProperty("jgroups.relay2.tcp.external_port", relay2.tcp.externalPort);
    if (relay2 != null && relay2.tcpping != null && relay2.tcpping.initialHosts != null) System.setProperty("jgroups.relay2.tcpping.initial_hosts", String.join(",", relay2.tcpping.initialHosts));
  }
}
