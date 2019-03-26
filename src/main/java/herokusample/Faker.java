/*
 * The MIT License
 *
 * Copyright 2019 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package herokusample;

/**
 * A dummy entity that randomly creates a name and ID pair.
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class Faker {

    private int id;
    private String name;
    private final String[] names = {"Zoraida", "Leigha", "Aurea", "Porsha", "Alona",
        "Chiquita", "Shane", "Jules", "Adeline", "Corrie", "Kareem", "Fritz",
        "Julia", "Camelia", "Henrietta", "Ginny", "Vernell", "Elma", "Bruna",
        "Sanda",};

    public Faker() {
        id = (int) Math.floor(Math.random() * 10000);
        name = names[(int) Math.floor(Math.random() * 20)];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
