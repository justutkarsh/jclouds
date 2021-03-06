/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
package org.jclouds.cloudwatch;

import com.google.common.reflect.TypeToken;
import org.jclouds.apis.BaseContextLiveTest;
import org.jclouds.cloudwatch.options.ListMetricsOptions;
import org.jclouds.rest.RestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Tests behavior of {@code CloudWatch}.
 *
 * @author Jeremy Whitlock
 */
public class CloudWatchLiveTest extends BaseContextLiveTest<RestContext<CloudWatchClient, CloudWatchAsyncClient>> {

   public CloudWatchLiveTest() {
      provider = "cloudwatch";
   }

   private CloudWatchClient client;

   @Override
   @BeforeClass(groups = { "integration", "live" })
   public void setupContext() {
      super.setupContext();
      client = context.getApi();
   }

   @Override
   protected TypeToken<RestContext<CloudWatchClient, CloudWatchAsyncClient>> contextType() {
      return CloudWatchApiMetadata.CONTEXT_TOKEN;
   }

   @Test
   protected void testCloudWatchListMetrics() {
      // Just make sure there is at least one metric returned (Much better if the account you use has more than 500)
      checkArgument(CloudWatch.listMetrics(client, null, ListMetricsOptions.builder().build()).iterator().hasNext());
   }

}
