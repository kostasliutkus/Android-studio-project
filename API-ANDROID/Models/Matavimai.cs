using System.ComponentModel.DataAnnotations;

namespace API_ANDROID.Models;

public class Matavimai
{
    [Key]
    public int Matavimas { get; set; }
    public int Y { get; set; }
    public int X { get; set; }
    public double Atstumas { get; set; }
    
}