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

import java.io.*;
import java.util.*;

import org.apache.maven.plugins.deploy.Utils;

def paths =
[
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.pom",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.pom.md5",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.pom.sha1",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.jar",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.jar.md5",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.jar.sha1",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0-sources.jar",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0-sources.jar.md5",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0-sources.jar.sha1",
    "org/apache/maven/its/deploy/ajc/test/maven-metadata.xml",
    "org/apache/maven/its/deploy/ajc/test/maven-metadata.xml.md5",
    "org/apache/maven/its/deploy/ajc/test/maven-metadata.xml.sha1",
]

def cksumToCheckPaths = [
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.pom",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0.jar",
    "org/apache/maven/its/deploy/ajc/test/1.0/test-1.0-sources.jar"
]

// Check if artifacts have been uploaded to remote with checksums
def repository = new File (basedir, "target/remoterepo" )
paths.each { path ->
    //File file = new File( localRepositoryPath, path );
    File file = new File( repository, path );
    println "Checking for existence of ${file}"
    if ( !file.isFile() )
    {
        throw new FileNotFoundException( "Missing: " + file.getAbsolutePath() );
    }
    if ( cksumToCheckPaths.contains( path ) )
    {    
        println "Verifying ${file}"
        Utils.verifyChecksum( file );
    }
}

return true;