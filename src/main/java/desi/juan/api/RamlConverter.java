/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package desi.juan.api;

import com.squareup.javapoet.JavaFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import desi.juan.internal.RetrofitInterface;

public class RamlConverter
{

    private final RetrofitInterface retrofitInterface;
    private final String filePackage;

    public RamlConverter(String name, String filePackage, String ramlLocation)
    {
        this.filePackage = filePackage;
        this.retrofitInterface = new RetrofitInterface(name, ramlLocation);
    }

    public void write(File directory) throws IOException
    {
        if (!directory.exists())
        {
            directory.createNewFile();
        }
        JavaFile.builder(filePackage, retrofitInterface.generate()).build().writeTo(directory);
    }

    public void write(Path path) throws IOException
    {
        write(path.toFile());
    }


}
