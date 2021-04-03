package ru.nsu.fit.sokolova.gui;

public class Edge
{
    private String name_;
    static int id_;

    public Edge(String newName)
    {
        id_++;
        name_ = newName;
    }
    public String toString() {
        return name_;
    }
}
