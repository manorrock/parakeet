/*
 *  Copyright (c) 2002-2021, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *      3. Neither the name of the copyright holder nor the names of its 
 *         contributors may be used to endorse or promote products derived from
 *         this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.yaml;

/**
 * The YAML Demo Pojo #2
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLDemoPojo3 {
    
    /**
     * Stores the pojo.
     */
    private YAMLDemoPojo1 pojo;
    
    /**
     * Stores the pojo.
     */
    private YAMLDemoPojo1 pojo2;
    
    /**
     * Get the pojo.
     * 
     * @return the pojo.
     */
    public YAMLDemoPojo1 getPojo() {
        return pojo;
    }
    
    /**
     * Get the pojo #2.
     * 
     * @return the pojo #2.
     */
    public YAMLDemoPojo1 getPojo2() {
        return pojo;
    }
    
    /**
     * Set the pojo.
     * 
     * @param pojo the pojo.
     */
    public void setPojo(YAMLDemoPojo1 pojo) {
        this.pojo = pojo;
    }
    
    /**
     * Set the pojo #2.
     * 
     * @param pojo2 the pojo #2.
     */
    public void setPojo2(YAMLDemoPojo1 pojo2) {
        this.pojo2 = pojo2;
    }
}
