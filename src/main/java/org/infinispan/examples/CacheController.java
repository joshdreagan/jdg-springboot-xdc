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

import org.infinispan.Cache;
import org.infinispan.context.Flag;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

  @Autowired
  private EmbeddedCacheManager cacheManager;

  @RequestMapping(path = "/caches/{cacheName}/{key}",
                  method = RequestMethod.GET,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public String get(@PathVariable String cacheName,
                    @PathVariable String key) {
    Cache<String, String> cache = cacheManager.getCache(cacheName);
    return cache.get(key);
  }

  @RequestMapping(path = "/caches/{cacheName}/{key}",
                  method = RequestMethod.PUT,
                  consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public String put(@PathVariable String cacheName,
                    @PathVariable String key,
                    @RequestParam(defaultValue = "false") boolean ignoreReturnValue,
                    @RequestBody(required = true) String value) {
    Cache<String, String> cache = cacheManager.getCache(cacheName);
    if (ignoreReturnValue) {
      return cache.getAdvancedCache().withFlags(Flag.IGNORE_RETURN_VALUES).put(key, value);
    } else {
      return cache.put(key, value);
    }
  }

  @RequestMapping(path = "/caches/{cacheName}/{key}",
                  method = RequestMethod.DELETE,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public String delete(@PathVariable String cacheName,
                       @PathVariable String key,
                       @RequestParam(defaultValue = "false") boolean ignoreReturnValue) {
    Cache<String, String> cache = cacheManager.getCache(cacheName);
    if (ignoreReturnValue) {
      return cache.getAdvancedCache().withFlags(Flag.IGNORE_RETURN_VALUES).remove(key);
    } else {
      return cache.remove(key);
    }
  }
}
