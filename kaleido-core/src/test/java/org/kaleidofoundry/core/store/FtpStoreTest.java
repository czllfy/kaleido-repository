/*  
 * Copyright 2008-2010 the original author or authors 
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
package org.kaleidofoundry.core.store;

import org.junit.After;
import org.junit.Before;
import org.kaleidofoundry.core.context.RuntimeContext;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

/**
 * @author Jerome RADUGET
 */
public class FtpStoreTest extends AbstractFileStoreTest {

   private FakeFtpServer fakeFtpServer;

   @Before
   @Override
   public void setup() throws Throwable {
	super.setup();

	// ftp mock setup
	fakeFtpServer = new FakeFtpServer();
	fakeFtpServer.setServerControlPort(43120);
	final FileSystem fileSystem = new UnixFakeFileSystem();
	fileSystem.add(new FileEntry("/kaleidofoundry/it/store/foo.txt", DEFAULT_RESOURCE_MOCK_TEST));
	fakeFtpServer.setFileSystem(fileSystem);
	fakeFtpServer.addUserAccount(new UserAccount("anonymous", "anonymous", "/"));
	fakeFtpServer.start();
	// end ftp mock setip

	final RuntimeContext<FileStore> context = new FileStoreContextBuilder().withUriRootPath("ftp://anonymous:anonymous@localhost:43120/").build();
	fileStore = new FtpStore(context);

	// anonymous account : ftp://hostname/resourcepath
	// account : ftp://username:password@hostname/resourcepath
	existingResources.put("kaleidofoundry/it/store/foo.txt", DEFAULT_RESOURCE_MOCK_TEST);
	nonExistingResources.add("kaleidofoundry/it/store/foo");
   }

   @After
   @Override
   public void cleanup() throws Throwable {
	super.cleanup();
	fakeFtpServer.stop();
   }
}