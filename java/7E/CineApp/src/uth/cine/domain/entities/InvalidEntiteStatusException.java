/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.domain.entities;

/**
 *
 * @author Neo
 */
public class InvalidEntiteStatusException extends RuntimeException {

    public InvalidEntiteStatusException() {
    }

    public InvalidEntiteStatusException(String s) {
        super(s);
    }

}
